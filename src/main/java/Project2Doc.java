import com.google.common.collect.Lists;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.textmining.text.extraction.WordExtractor;

import java.io.*;
import java.util.List;

/**
 * Created by zhugongyi on 2017/8/1.
 */
public class Project2Doc {

    private static List<File> scanedFiles = Lists.newArrayList();

    public void scan(String path) {
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                return;
            } else {
                for (File file1 : files) {
                    if (file1.isDirectory() && !file1.getName().endsWith(".idea")) {
                        scan(file1.getAbsolutePath());
                    } else {
                        if (file1.getName().endsWith(".java") || file1.getName().endsWith(".xml")) {
                            scanedFiles.add(file1);
                        }
                    }
                }
            }
        }
    }

    public String readFile(File file)  {
        // 创建输入流读取DOC文件
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        WordExtractor extractor = null;
        String text = null;
        // 创建WordExtractor
        extractor = new WordExtractor();
        // 对DOC文件进行提取
        try {
            text = extractor.extractText(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }

    public boolean writeDoc(String path, String content) {
        boolean w = false;
        try {
            // byte b[] = content.getBytes("ISO-8859-1");
            byte b[] = content.getBytes();
            ByteArrayInputStream bais = new ByteArrayInputStream(b);
            POIFSFileSystem fs = new POIFSFileSystem();
            DirectoryEntry directory = fs.getRoot();
            DocumentEntry de = directory.createDocument("WordDocument", bais);
            FileOutputStream ostream = new FileOutputStream(path);
            fs.writeFilesystem(ostream);
            bais.close();
            ostream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return w;
    }

    public static void main(String[] args) {
        Project2Doc doc = new Project2Doc();
        doc.scan(new File("").getAbsolutePath());
        scanedFiles.forEach(e -> {
            String text = doc.readFile(e);
            doc.writeDoc("code.docx", text);
        });
    }

}
