package com.azienda.catalogoProdotti.utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.engine.jdbc.BlobProxy;

import com.azienda.catalogoProdotti.model.Item;

public class BlobConverter {
	
	public static Blob generateBlob(String filePath) throws Exception{
		InputStream is = new FileInputStream(filePath);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte [] buffer = new byte[1024];
		int read = -1;
		while ( (read = is.read(buffer, 0, buffer.length)) != -1)  {
			baos.write(buffer, 0, read);
		}
		baos.flush();
		byte [] data = baos.toByteArray();
		Blob result = BlobProxy.generateProxy(data);
		baos.close();
		is.close();
		return result;
	}
	
	public static void saveFile(Blob blob,String filePath) throws Exception {
		InputStream is = blob.getBinaryStream();
    	FileOutputStream fos = new FileOutputStream(filePath);
		byte [] buffer = new byte[1024];
		int read = -1;
		while ( (read = is.read(buffer, 0, buffer.length)) != -1)  {
			fos.write(buffer, 0, read);
		}
		fos.flush();
		fos.close();
	}
	
	public static void addImage(HttpServletRequest request, List<Item> itemList) throws Exception {
		String uploadPath = request.getServletContext().getRealPath("") + File.separator + "upload";
		File uploadDir = new File(uploadPath);
		String basePath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		Map<Integer, String> imageMap = new HashMap<Integer,String>();
		for(Item x : itemList) {
			String filePath = uploadDir + File.separator + x.getId() + "_" + x.getImageName();
			if(x.getImage() != null) {
				BlobConverter.saveFile(x.getImage(), filePath);
				String imgPath = basePath + File.separator + "upload" + File.separator + x.getId() + "_" + x.getImageName();
				imageMap.put(x.getId(), imgPath);
			} else {
				String imgPath = basePath + File.separator + "img" + File.separator + "theoffice.gif";
				imageMap.put(x.getId(), imgPath);
			}
		}
		request.setAttribute("costanteMappa", imageMap);
	}

}
