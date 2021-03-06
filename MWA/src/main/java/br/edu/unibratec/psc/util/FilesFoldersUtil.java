package br.edu.unibratec.psc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FilesFoldersUtil {
	
	public static final String TEST_XLSX_FILENAME = "test.xlsx";
	
	
	public static String getAbsolutePathToSrcTestResourceImages_JSF_PNG_File() {
		String relativePath = 
			getPathDirectory()								+
			getRelativePathToSrcTestResourceImagesFolder()	+ 	getFolderSeparator()	+	
			"jsf.png"
		;
		return relativePath;
	}
	
	public static String getRelativePathToSrcTestResourceImagesFolder() {
		String relativePath = 
			getRelativePathToSrcTestResourceFolder()	+ 
			"images"
		;
		return relativePath;
	}
	
	public static String getRelativePathToSrcTestResourceFolder() {
		String relativePath = 
			getFolderSeparator()	+ "src" + 
			getFolderSeparator()	+ "test" + 
			getFolderSeparator()	+ "resources" + 
			getFolderSeparator();
		return relativePath;
	}
	
	public static String getRelativePathToSrcMainResourceFolder() {
		String relativePath = 
			getFolderSeparator()	+ "src" + 
			getFolderSeparator()	+ "main" + 
			getFolderSeparator()	+ "resources" + 
			getFolderSeparator();
		return relativePath;
	}
	
	public static String getFolderSeparator() {
		String folderSeparator = System.getProperty("file.separator");
		return folderSeparator;
	}
	
	public static String getPathDirectory() {
		String path = System.getProperty("user.dir");
		return path;
	}
	
	public static String getOperationalSystemName() {
		return System.getProperty("os.name");
	}
	
	public static String getFullPathToSrcTestResourceFolder() {
		String fullPath = getPathDirectory() + getRelativePathToSrcTestResourceFolder();
		return fullPath;
	}
	
	public static File createFile_SrcTestResource( String pFileName ) throws IOException {
		File file = 
			new File(
					getFullPathToSrcTestResourceFolder() + getFolderSeparator() + pFileName
			);
		
		file.createNewFile();
		
		return file;
	}
	
	public static File createFile( String pFullFilePathAndName ) throws IOException {
		File file = 
			new File(
				pFullFilePathAndName
			);
		
		return file;
	}
	
	public static InputStream createInputStream( String pFileName ) throws IOException {
		InputStream inputStream = 
			new FileInputStream(
				createFile(pFileName)
			);
		
		return inputStream;
	}
	
	public static FileInputStream createFileInputStream( String pFileName ) throws IOException {
		FileInputStream fileInputStream = new FileInputStream(createFile(pFileName));
		
		return fileInputStream;
	}
	
	public static byte[] getByteArrayFromSrcTestResourceImages_JSF_PNG_File() throws IOException {
		byte[] response = null;
		
		String fullPath = 
			FilesFoldersUtil.getAbsolutePathToSrcTestResourceImages_JSF_PNG_File();
		
		FileInputStream fis = createFileInputStream(fullPath);
		response 	= 	fis.readAllBytes();
		
		return response;
	}
	
}