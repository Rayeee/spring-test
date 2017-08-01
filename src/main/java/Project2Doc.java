import com.google.common.collect.Lists;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

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
            }else{
                for (File file1 : files) {
                    if (file1.isDirectory() && !file1.getName().endsWith(".idea")) {
                        scan(file1.getAbsolutePath());
                    } else {
                        if(file1.getName().endsWith(".java") || file1.getName().endsWith(".xml")){
                            scanedFiles.add(file1);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Project2Doc doc = new Project2Doc();
        doc.scan(new File("").getAbsolutePath());
        scanedFiles.forEach(e -> {
            XWPFDocument document= new XWPFDocument();
            try {
                FileOutputStream out = new FileOutputStream(e);
                document.write(out);
                out.close();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

    }

}
