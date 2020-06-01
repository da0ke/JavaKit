package cn.da0ke.javakit.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;

import net.coobird.thumbnailator.Thumbnails;

public class FileApi {
private FileApi(){}
	
	public static Builder builder(){
        return new Builder();
    }
	
	/**
	 * 验证图片文件
	 * @param file
	 * @param fileName
	 * @param maxFileSize 单位KB
	 * @return
	 */
	public static int validUploadImage(File file, String fileName, int maxFileSize) {
		int code;
		if(file == null) {
			//空文件
			code = 101;
		} else {
			/**
			 * 判断图片文件合法性
			 */
			String ext = FilenameUtils.getExtension(fileName).toLowerCase();
			if(StringUtils.isTrimEmpty(ext) || 
					(!ext.endsWith("jpg") && !ext.endsWith("png") && !ext.endsWith("jpeg"))) {
				//文件名不正确
				code = 102;
			} else if(!isImage(file)) {
				//不是有效的图片文件
				code = 103;
			} else if(maxFileSize>0 && FileUtils.sizeOf(file) > maxFileSize*1024) {
				//图片文件不能超过{maxFileSize}KB
				code = 104;
			} else {
				code = 999;
			}
		}
		return code;
	}
	
	public static int validUploadImage(File file, String fileName, boolean validFileSize) {
		return validUploadImage(file, fileName, validFileSize?200:0);
	}
	
	public static int validUploadImage(File file, String fileName) {
		return validUploadImage(file, fileName, true);
	}
	
	public static class Builder{
		/**
		 * 文件
		 */
		private File file;
		/**
		 * 文件链接
		 */
		private String url;
		/**
		 * 是否压缩
		 */
		private boolean compress = false;
		/**
		 * 水印图片
		 */
		private BufferedImage watermark;
		/**
		 * 水印图片透明度
		 */
		private float opacity = 0.2f;
		/**
		 * 根目录
		 */
		private String root;
		/**
		 * 来源文件名
		 */
		private String fileName;
		/**
		 * 目标文件名
		 * 不带目录，比如：12345.jpg
		 */
		private String destFilename;
		/**
		 * 是否需要生成小图
		 */
		private boolean hasSmall = false;
		/**
		 * 小图宽度限制
		 */
		private int smallWidth = 200;
		/**
		 * 小图高度限制
		 */
		private int smallHeight = 200;
		/**
		 * 大图宽度限制
		 */
		private int bigWidth = 800;
		/**
		 * 大图高度限制
		 */
		private int bigHeight = 800;
		
		public Builder root(String root){
            this.root = root;
            return this;
        }
		
		public Builder file(File file) {
			this.file = file;
			return this;
		}
		
		public Builder url(String url){
            this.url = url;
            return this;
        }
		
		public Builder fileName(String fileName) {
			this.fileName = fileName;
			return this;
		}
		
		public Builder destFilename(String destFilename) {
			this.destFilename = destFilename;
			return this;
		}
		
		public Builder compress(boolean compress) {
			this.compress = compress;
			return this;
		}
		
		public Builder hasSmall(boolean hasSmall) {
			this.hasSmall = hasSmall;
			return this;
		}
		
		public Builder watermark(BufferedImage watermark) {
			this.watermark = watermark;
			return this;
		}
		
		public Builder watermark(BufferedImage watermark, float opacity) {
			this.watermark = watermark;
			this.opacity = opacity;
			return this;
		}
		
		/**
		 * 文件路径没有按月份区分，适用于旧有的方式
		 * @return
		 */
		public String create2() {
			String result = null;
			
			String newFileName;
			if(file != null) {
				newFileName = generateFileName(fileName);
				String outFilepath = root+newFileName;
				
				if(!new File(root).exists()) {
					new File(root).mkdirs();
				}
				
				try {
					if(hasSmall) {
						String smallPath = root+"s_"+newFileName;
						Thumbnails.of(file).size(smallWidth, smallHeight).toFile(smallPath);
					}
					
					if(watermark != null) {
						Thumbnails.of(file).size(bigWidth, bigHeight).watermark(watermark,opacity).toFile(outFilepath);
					} else if(compress) { //压缩
						Thumbnails.of(file).size(bigWidth, bigHeight).toFile(outFilepath);
					} else {
						FileUtils.copyFile(file, new File(outFilepath));
					}

					result = newFileName;
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if(StringUtils.isNotEmpty(url)) {
				newFileName = generateFileName();
				String outFilepath = root+newFileName;
				
				if(!new File(root).exists()) {
					new File(root).mkdirs();
				}
				

				try {
					InputStream inStream = NetUtils.builder().url(url).syncGetInputStream();
					
					if(watermark != null) {
						Thumbnails.of(inStream).size(bigWidth, bigHeight).watermark(watermark,opacity).toFile(new File(outFilepath));
					} else {
						Thumbnails.of(inStream).size(bigWidth, bigHeight).toFile(new File(outFilepath));
					}
					
					if(hasSmall) {
						String smallDes = root+"s_"+newFileName;
						Thumbnails.of(new File(outFilepath)).size(smallWidth, smallHeight).toFile(smallDes);
					}
					
					result = newFileName;
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}

			return result;
		}
		
		/**
		 * 文件路径按月份区分
		 * @return
		 */
		public String create() {
			String result = null;
			
			String newFileName;
			if(file != null) {
				if(destFilename == null) {
					newFileName = generateFileName(fileName);
				} else {
					newFileName = destFilename;
				}
				
				String folderName = generateFolderNameByMonth();
				String outFilepath = root+folderName+File.separator+newFileName;
				
				if(!new File(root+folderName).exists()) {
					new File(root+folderName).mkdirs();
				}
				
				try {
					if(hasSmall) {
						String smallPath = root+folderName+File.separator+"s_"+newFileName;
						Thumbnails.of(file).size(smallWidth, smallHeight).toFile(smallPath);
					}

					if(watermark != null) { //加水印，则必须进行压缩
						Thumbnails.of(file).size(bigWidth, bigHeight).watermark(watermark,opacity).toFile(outFilepath);
					} else if(compress) { //压缩
						Thumbnails.of(file).size(bigWidth, bigHeight).toFile(outFilepath);
					} else {
						FileUtils.copyFile(file, new File(outFilepath));
					}
					
					result = folderName + "/" + newFileName;
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if(StringUtils.isNotEmpty(url)) {
				newFileName = generateFileName();
				String folderName = generateFolderNameByMonth();
				String outFilepath = root+folderName+File.separator+newFileName;
				
				if(!new File(root+folderName).exists()) {
					new File(root+folderName).mkdirs();
				}
				
				try {
					InputStream inStream = NetUtils.builder().url(url).syncGetInputStream();
					
					if(hasSmall) {
						String smallDes = root+folderName+File.separator+"s_"+newFileName;
						Thumbnails.of(inStream).size(smallWidth, smallHeight).toFile(smallDes);
					}
					
					/**
					 * url下载图片，必须压缩
					 */
					if(watermark != null) { //加水印
						Thumbnails.of(inStream).size(bigWidth, bigHeight).watermark(watermark,opacity).toFile(new File(outFilepath));
					} else {
						Thumbnails.of(inStream).size(bigWidth, bigHeight).toFile(new File(outFilepath));
					}
					
					result = folderName + "/" + newFileName;
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}

			return result;
		}
		
		/**
		 * 2019-12-09/xxxxx.jpg
		 * @return
		 */
		public String create3() {
			String result = null;
			
			String newFileName;
			if(file != null) {
				if(destFilename == null) {
					newFileName = generateFileName(fileName);
				} else {
					newFileName = destFilename;
				}
				
				String folderName = generateFolderNameByDate();
				String outFilepath = root+folderName+File.separator+newFileName;
				
				if(!new File(root+folderName).exists()) {
					new File(root+folderName).mkdirs();
				}
				
				try {
					if(hasSmall) {
						String smallPath = root+folderName+File.separator+"s_"+newFileName;
						Thumbnails.of(file).size(smallWidth, smallHeight).toFile(smallPath);
					}

					if(watermark != null) { //加水印，则必须进行压缩
						Thumbnails.of(file).size(bigWidth, bigHeight).watermark(watermark,opacity).toFile(outFilepath);
					} else if(compress) { //压缩
						Thumbnails.of(file).size(bigWidth, bigHeight).toFile(outFilepath);
					} else {
						FileUtils.copyFile(file, new File(outFilepath));
					}
					
					result = folderName + "/" + newFileName;
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if(StringUtils.isNotEmpty(url)) {
				newFileName = generateFileName();
				String folderName = generateFolderNameByDate();
				String outFilepath = root+folderName+File.separator+newFileName;
				
				if(!new File(root+folderName).exists()) {
					new File(root+folderName).mkdirs();
				}
				
				try {
					InputStream inStream = NetUtils.builder().url(url).syncGetInputStream();
					
					if(hasSmall) {
						String smallDes = root+folderName+File.separator+"s_"+newFileName;
						Thumbnails.of(inStream).size(smallWidth, smallHeight).toFile(smallDes);
					}
					
					/**
					 * url下载图片，必须压缩
					 */
					if(watermark != null) { //加水印
						Thumbnails.of(inStream).size(bigWidth, bigHeight).watermark(watermark,opacity).toFile(new File(outFilepath));
					} else {
						Thumbnails.of(inStream).size(bigWidth, bigHeight).toFile(new File(outFilepath));
					}
					
					result = folderName + "/" + newFileName;
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}

			return result;
		}
		
		/**
		 * [photo|middlePhoto|smallPhoto]/2019-12-09/xxxxx.jpg
		 * @return
		 */
		public String create4() {
			String result = null;
			
			String newFileName;
			if(file != null) {
				if(destFilename == null) {
					newFileName = generateFileName(fileName);
				} else {
					newFileName = destFilename;
				}
				
				String bigDir = "photo";
				String middleDir = "middlePhoto";
				String smallDir = "smallPhoto";
				
				String folderName = generateFolderNameByDate();
				String outFilepath = root+bigDir+File.separator+folderName+File.separator+newFileName;
				
				if(!new File(root+bigDir+File.separator+folderName).exists()) {
					new File(root+bigDir+File.separator+folderName).mkdirs();
				}
				
				try {
					// 有大图和中图
					if(hasSmall) {
						if (!new File(root+middleDir+File.separator+folderName).exists()) {
							new File(root+middleDir+File.separator+folderName).mkdirs();
						}
						
						if (!new File(root+smallDir+File.separator+folderName).exists()) {
							new File(root+smallDir+File.separator+folderName).mkdirs();
						}
						
						String middlePath = root+middleDir+File.separator+folderName+File.separator+newFileName;
						Thumbnails.of(file).size(250, 250).toFile(middlePath);
						
						String smallPath = root+smallDir+File.separator+folderName+File.separator+newFileName;
						Thumbnails.of(file).size(100, 100).toFile(smallPath);
					}

					if(watermark != null) { //加水印，则必须进行压缩
						Thumbnails.of(file).size(bigWidth, bigHeight).watermark(watermark,opacity).toFile(outFilepath);
					} else if(compress) { //压缩
						Thumbnails.of(file).size(bigWidth, bigHeight).toFile(outFilepath);
					} else {
						FileUtils.copyFile(file, new File(outFilepath));
					}
					
					result = folderName + "/" + newFileName;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			return result;
		}
		
		/**
		 * 依据原始文件名后缀,生成新文件名
		 * 
		 * @param fileName
		 * @return
		 */
		private String generateFileName(String fileName) {
			//文件扩展名
			String ext = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
			return RandomUtils.nextInt(0, 10000) + "" + System.currentTimeMillis() + ext;
		}
		
		private String generateFileName() {
			return RandomUtils.nextInt(0, 10000) + "" + System.currentTimeMillis() + ".jpg";
		}
		
		/**
		 * 根据月份生成目录名称
		 * 
		 * @param path
		 * @return
		 */
		private String generateFolderNameByMonth() {
			SimpleDateFormat formater = new SimpleDateFormat("yyyyMM");
			return formater.format(new Date());
		}
		
		
		private String generateFolderNameByDate() {
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			return formater.format(new Date());
		}
		
	}
	
	/**
	 * 文件是否是图片
	 * 
	 * @param file
	 * @return
	 */
	private static boolean isImage(File file) {
		boolean result = false;
        if (file.isFile()) {
        	Tika tika = new Tika();
            try {
    			String fileType = tika.detect(file);
    			if(fileType.equals("image/png") || fileType.equals("image/jpg") || fileType.equals("image/jpeg")) {
    				result = true;
    			}
    		} catch (IOException e1) {
    			e1.printStackTrace();
    		}
        }
        return result;
    }
	
}
