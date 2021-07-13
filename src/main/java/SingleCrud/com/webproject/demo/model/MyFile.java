package SingleCrud.com.webproject.demo.model;

import org.springframework.web.multipart.MultipartFile;


public class MyFile {
    private MultipartFile multipartFile;

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public MyFile() {

    }

    public MyFile(MultipartFile multipartFile) {
        this.multipartFile =  multipartFile;
    }
}
