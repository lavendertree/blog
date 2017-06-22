package blog.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by weber on 2017/5/28.
 */

@RestController
public class FileUploadController {

    @Autowired
    private HttpServletRequest request;

    /*
    *@function 上传文件
    * return
    */
    @RequestMapping (value="/upload")
    public boolean Upload(@RequestParam("file") MultipartFile uploadFile)throws Exception{
        if (!uploadFile.isEmpty()){
            //获取文件名
            String filename=uploadFile.getOriginalFilename();
            //前半部分路径，目录，将WebRoot下一个名称为upload文件夹转为绝对路径
            String leftPath=request.getSession().getServletContext().getRealPath("/static/upload");
            //转存,进行路径拼接=前半部分路径+文件名
            uploadFile.transferTo(new File(leftPath,filename));
        }
        else{
            return false;
        }
        return true;
    }

    /*
    *@function 读取所有的文件并返回
    * return
     */
    @GetMapping(value = "/listfiles")
    public ModelAndView list(){
        String filePath=request.getSession().getServletContext().getRealPath("/")+"static/upload/";
        ModelAndView mav=new ModelAndView("list");
        File uploadDest=new File(filePath);
        String[] fileNames=uploadDest.list();
        mav.addObject(fileNames);
        return mav;
    }

    /*
  *@function 根据文件名进行下载
  * @param filename
  * return
   */
    @RequestMapping(value = "/download")
    public ResponseEntity<byte[]> download(String filename)throws IOException {
        String path=request.getSession().getServletContext().getRealPath("/")+"static/upload/";
        File file=new File(path,filename);
        HttpHeaders headers=new HttpHeaders();
        //解决中文乱码问题
        String fileName=new String(filename.getBytes("UTF-8"),"iso-8859-1");
        headers.setContentDispositionFormData("attachment",fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }

    @RequestMapping(value="deletefile")
    public boolean deleteFile(String filename){
        boolean flag=false;
        String path=request.getSession().getServletContext().getRealPath("/")+"static/upload/";
        File file=new File(path,filename);
        if (file.isFile()&&file.exists()){
            file.delete();
            flag=true;
        }
        return flag;
    }
}

