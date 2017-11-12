package wordgraph;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;


/**
 * Make dot script for Graph and run graphviz to generate a png image of graph.
 * @author HanYue
 *
 */
public class ShowGraph {
    /*get new line*/static String newLine=System.getProperty("line.separator");    //��õ�ǰϵͳ�Ļ��з�
    static String osName=System.getProperty("os.name");
    
    /**
     * build dot script for graph G.
     * @param G Graph
     */
    public static void showDirectedGraph(Graph G) {
        StringBuilder dotText=new StringBuilder();    //StringBuilder������Ч��Ҫ������String�ӼӼ�
        dotText.append(String.format("digraph G{"+newLine));    //д�뿪ͷ
        for(Node node:G.getNodeList()) {    //��������д�붥������
            dotText.append(node.name);
            if(!node.color.equals("black"))dotText.append(String.format(" [style=filled, fillcolor=%s]",node.color));
            dotText.append(";"+newLine);
        }
        dotText.append(newLine);
        for(Node node:G.getNodeList()) {    //������д�������
            for(Edge edge:node.edges) {
                dotText.append(String.format("%s->%s[label=%d]", edge.from,edge.to,edge.weight));
                if(!edge.color.equals("black"))dotText.append(String.format("[style=bold, color=%s]",edge.color));
                dotText.append(";"+newLine);
            }
        }
        dotText.append("}"+newLine);    //д�����
        
        //�����ɺõĽű�д��ָ���Ļ���·����
        String graphFilePath=Config.tmpPath+"graph.gv";
        try {
            File tmpf=new File(Config.tmpPath);
            if(!tmpf.exists()) {
                tmpf.mkdirs();
            }
            FileWriter fw=new FileWriter(graphFilePath);
            BufferedWriter bufw = new BufferedWriter(fw);
            bufw.write(dotText.toString());
            bufw.close();
        }catch (Exception e) {
            throw new RuntimeException("Failed to open file");
        }
        
        generateImage(graphFilePath);
    }
    
    /**
     * run graphviz on different system. 
     * @param filename dot script filePath
     */
    private static void generateImage(String filename) {
        if(osName.startsWith("win")||osName.startsWith("Win")) {    //�ж�ϵͳʹ�ò�ͬ�ĵ��÷���
            generateImageForWindows(filename);
        }
        else {
            generateImageForLinux(filename);
        }
    }
    
    /**
     * run graphviz on windows. 
     * @param filename filename dot script filePath
     */
    private static void generateImageForWindows(String filename) {
        Runtime rt=Runtime.getRuntime();    //ʹ��Runtimeִ��cmd����
        try {
            String[] args= {Config.dotForWindows,filename,"-Tpng","-o",Config.tmpPath+"img.png"};
            Process process = rt.exec(args);
            process.waitFor();
            
        }catch (Exception e) {
            throw new RuntimeException("Failed to generate image.");
        }
        
    }
    
    /**
     * run graphviz on unix. 
     * @param filename filename dot script filePath
     */
    private static void generateImageForLinux(String filename) {
        Runtime rt=Runtime.getRuntime();    ////ʹ��Runtimeִ��bash����
        try {
            String[] args= {"/bin/sh", "-c", Config.dotForLinux,filename,"-Tpng","-o",Config.tmpPath+"img.png"};
            Process process = rt.exec(args);
            process.waitFor();
            
        }catch (Exception e) {
            throw new RuntimeException("Failed to generate image.");
        }
        
    }
    
}