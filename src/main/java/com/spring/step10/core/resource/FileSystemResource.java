package com.spring.step10.core.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author 彭锦波
 * @project small-spring
 * @description 文件资源加载
 * @date 2023/12/20 12:12:24
 */
public class FileSystemResource implements Resource {
  private String filePath;

  private File file;

  public FileSystemResource(File file){
    this.file = file;
    this.filePath = file.getPath();
  }

  public FileSystemResource(String filePath){
    this.filePath = filePath;
    this.file = new File(filePath);
  }

  @Override
  public InputStream getInputStream() throws FileNotFoundException {
    return new FileInputStream(this.file);
  }
}
