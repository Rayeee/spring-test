import com.google.common.collect.Lists;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

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

    public String readFile(File file) throws IOException {
        String text = "";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                text = text.concat(tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return text;
    }

    public boolean writeDoc(String path, String content) {
        boolean w = false;
        try {
             byte b[] = content.getBytes("UTF-8");
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
            String text = null;
            try {
                text = doc.readFile(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            doc.writeDoc("code.docx", text);
        });
    }

}
