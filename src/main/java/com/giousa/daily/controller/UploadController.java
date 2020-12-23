package com.giousa.daily.controller;

import com.giousa.daily.bean.ResultVO;
import com.giousa.daily.enums.ResultEnum;
import com.giousa.daily.utils.UploadOSSUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("upload")
public class UploadController {

    @PostMapping("uploadSingleFile")
    public ResultVO uploadSingleFile(@RequestParam("type") String fileName,
                                     @RequestParam(value = "file") MultipartFile file) {

        String originalFilename = file.getOriginalFilename();

        //注意：当分隔符是.或者|的时候，需要转义
        String[] split = originalFilename.split("\\.");
        if(split.length != 2){
            return ResultVO.error(ResultEnum.FILE_UPLOAD_SUFFIX_FAIL);
        }

        String suffix = split[1];

        try {
            return ResultVO.ok(UploadOSSUtils.uploadSinglePic(file,fileName,"."+suffix));
        } catch (Exception e) {
            e.printStackTrace();
         }
        return ResultVO.error(ResultEnum.FILE_UPLOAD_FAIL);
    }

}
