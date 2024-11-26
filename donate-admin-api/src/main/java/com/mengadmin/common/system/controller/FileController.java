package com.mengadmin.common.system.controller;

import cn.hutool.core.util.StrUtil;
import com.mengadmin.common.core.annotation.OperationLog;
import com.mengadmin.common.core.config.ConfigProperties;
import com.mengadmin.common.core.utils.FileServerUtil;
import com.mengadmin.common.core.web.ApiResult;
import com.mengadmin.common.core.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * 文件上传下载控制器
 */
@Api(tags = "文件上传下载")
@RestController
@RequestMapping("/api/file")
public class FileController extends BaseController {
    @Resource
    private ConfigProperties config;

    @OperationLog
    @ApiOperation("上传文件")
    @PostMapping("/upload")
    public ApiResult<?> upload(@RequestParam MultipartFile file, HttpServletRequest request) {
        try {
            String dir = getUploadDir();
            File upload = FileServerUtil.upload(file, dir, config.getUploadUuidName());
            String path = upload.getAbsolutePath().replace("\\", "/").substring(dir.length() - 1);
            String requestURL = StrUtil.removeSuffix(request.getRequestURL(), "/upload");
            String originalName = file.getOriginalFilename();
            String url = requestURL + path;
            return success(url);
        } catch (Exception e) {
            e.printStackTrace();
            return fail("上传失败").setError(e.toString());
        }
    }

    /**
     * 文件上传基目录
     * TODO
     * windows使用 File.listRoots()方式
     * Mac 使用下面指定目录方式
     */
    private String getUploadBaseDir() {
        // 获取操作系统
        String os = System.getProperty("os.name");
        // 判断
        if (os.toLowerCase().startsWith("win")) {
            // 如果是Windows系统，默认是c盘
            return File.listRoots()[config.getUploadLocation()].getAbsolutePath()
                .replace("\\", "/") + "/upload/";
        } else {
            return "/Users/yaoyaomice/uploadFile/";
        }
    }

    /**
     * 文件上传位置
     */
    private String getUploadDir() {
        return getUploadBaseDir() + "file/";
    }

    /**
     * 缩略图生成位置
     */
    private String getUploadSmDir() {
        return getUploadBaseDir() + "thumbnail/";
    }

    /**
     * office转pdf输出位置
     */
    private String getPdfOutDir() {
        return getUploadBaseDir() + "pdf/";
    }

}
