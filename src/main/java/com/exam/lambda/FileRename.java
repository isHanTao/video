package com.exam.lambda;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface FileRename {
    /**
     *
     * @param file 上传的原始文件流
     * @param request 请求
     * @return 返回相对路径，可以创建文件夹
     */
    String rename(MultipartFile file, HttpServletRequest request);

}
