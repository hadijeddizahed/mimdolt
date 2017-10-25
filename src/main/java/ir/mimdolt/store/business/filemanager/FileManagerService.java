package ir.mimdolt.store.business.filemanager;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * Created by Hadi Jeddi Zahed on 11/9/2016.
 */
public interface FileManagerService {


    HashMap<String, Object> createFolder(HashMap<String, String> params) throws Exception;

    HashMap<String, Object> renameUrl(HashMap<String, String> params) throws Exception;

    void remove(String path) throws Exception;

    void upload(HttpServletRequest request, HttpServletResponse response) throws Exception;

    byte[] download(String action, String path)throws Exception;
}
