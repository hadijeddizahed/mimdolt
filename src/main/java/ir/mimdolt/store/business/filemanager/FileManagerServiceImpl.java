package ir.mimdolt.store.business.filemanager;

import ir.mimdolt.store.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;

/**
 * Created by Hadi Jeddi Zahed on 11/9/2016.
 */
@Service
public class FileManagerServiceImpl implements FileManagerService {
    private String REPOSITORY_BASE_PATH = "E:/Hadi";

    @Autowired
    private ServletContext servletContext;


    @Override
    public HashMap<String, Object> createFolder(HashMap<String, String> params) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            String path = params.get("action");
            String name = params.get("newPath");

            File newDir = new File(REPOSITORY_BASE_PATH + name);
            if (!newDir.mkdir()) {
                throw new Exception("Can't create directory: " + newDir.getAbsolutePath());
            }
            result.put("result", new Result(true, null));
            return result;
        } catch (Exception e) {
            result.put("result", new Result(false, e.getMessage()));
            return result;
        }
    }

    @Override
    public HashMap<String, Object> renameUrl(HashMap<String, String> params) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        File oldFile = new File(REPOSITORY_BASE_PATH + params.get("item").toString());
        File newFile = new File(REPOSITORY_BASE_PATH + params.get("newItemPath"));
        if (newFile.exists()) {
            result.put("result", new Result(false, "Duplicate File Name"));
            return result;
        }
        oldFile.renameTo(newFile);
        result.put("result", new Result(true, null));
        return result;
    }

    @Override
    public void remove(String path) throws Exception {
        byte[] hashedPath = Base64.getDecoder().decode(path);
        Path path1 = Paths.get(new String(hashedPath));
        Files.deleteIfExists(path1);
    }

    @Override
    public void upload(HttpServletRequest request, HttpServletResponse response) throws Exception {

    }

    @Override
    public byte[] download(String action, String path) throws Exception {
        byte[] hashedPath = Base64.getDecoder().decode(path);
        Path path1 = Paths.get(new String(hashedPath));
        return Files.readAllBytes(path1);
    }
}
