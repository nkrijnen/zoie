package proj.zoie.api.impl.util;
/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtil {
	/**
	   * utility method to delete a directory
	   * @param dir
	   * @throws IOException
	   */
	  private static void deleteDir(File dir)
	  {
	    if (dir == null) return;
	    
	    if (dir.isDirectory())
	    {
	      File[] files=dir.listFiles();
	      for (File file : files)
	      {
	        deleteDir(file);
	      }
	      dir.delete();
	    }
	    else
	    {
	      dir.delete();
	    }
	  }

	  /**
	   * Purges an index
	   */
	  public static void rmDir(File location)
	  {
	    String name=location.getName()+"-"+System.currentTimeMillis();
	    File parent=location.getParentFile();
	    File tobeDeleted=new File(parent,name);
	    location.renameTo(tobeDeleted);
	    // try to delete the files, ok if it fails, this is just for testing
	    deleteDir(tobeDeleted);
	  }
	public static long sizeFile(File location)
	{
	  if (location ==null || !location.exists()) return 0;
	  long total = 0;
    if (location.isDirectory())
    {
      File[] files=location.listFiles();
      for (File file : files)
      {
        total += sizeFile(file);
      }
      return total;
    }
    else
    {
      return location.length();
    }
	}
	
	public static void copyFile(File src, File dst) throws IOException 
	{
	  InputStream in = null;
	  OutputStream out = null;
	  try
	  {
		in = new FileInputStream(src);
	    out = new FileOutputStream(dst);

	    byte[] buf = new byte[4096];
        int len;
	    while ((len = in.read(buf)) > 0) 
	    {
	      out.write(buf, 0, len);
	    }
	  }
	  finally
	  {
	    if (in != null)
	    {
		  in.close();
	    }
		if (out != null)
		{
	      out.close();   
		}
	  }
	}
}
