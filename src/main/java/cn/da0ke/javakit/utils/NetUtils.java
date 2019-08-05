package cn.da0ke.javakit.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 
 * @author da0ke
 *
 */
public class NetUtils {
	
	private static final OkHttpClient HTTP_CLIENT = new OkHttpClient();
	
	public interface CallBack {
        void onSuccess(String value);
        void onFail();
    }

    private NetUtils() {

    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {

        private String url;
        private Map<String,String> params = new HashMap<>();
        private Map<String,File> fileParams = new HashMap<>();
        

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder add(String key, File file) {
        	fileParams.put(key, file);
            return this;
        }

        public Builder add(String key, String value) {
        	// 当null时，遍历map时会出错，所以需要将null转为空
            params.put(key,StringUtils.trimToEmpty(value));
            return this;
        }
        public Builder add(String key, int value) {
            params.put(key,String.valueOf(value));
            return this;
        }
        public Builder add(String key, boolean value) {
            params.put(key,String.valueOf(value));
            return this;
        }
        
        public String syncGet() {
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            String value = null;
			try {
				Response response = HTTP_CLIENT.newCall(request).execute();
				value = response.body().string();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return value;
        }
        
        public InputStream syncGetInputStream() {
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            
            InputStream value = null;
            try {
				Response response = HTTP_CLIENT.newCall(request).execute();
				value = response.body().byteStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return value;
        }

        public void get(final CallBack callBack) {
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            HTTP_CLIENT.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    callBack.onFail();
                }

                @Override
                public void onResponse(Call call, Response response) {
                    try {
                        String value = response.body().string();
                        callBack.onSuccess(value);
                    } catch(Exception e) {
                        callBack.onFail();
                    }
                }
            });
        }

        public void post(final CallBack callBack) {
            RequestBody body;
            if(fileParams.isEmpty()) {
                FormBody.Builder builder = new FormBody.Builder();
                if(!params.isEmpty()) {
                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        builder.add(entry.getKey(), entry.getValue());
                    }
                }
                body = builder.build();
            } else {
                MultipartBody.Builder builder = new MultipartBody.Builder();
                for (Map.Entry<String, File> entry : fileParams.entrySet()) {
                	builder.addFormDataPart(entry.getKey(), entry.getValue().getName()
                			, RequestBody.create(MediaType.parse(getMimeType(entry.getValue().getName())), entry.getValue()));
                }
                
                builder.setType(MultipartBody.FORM);
                if(!params.isEmpty()) {
                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        builder.addFormDataPart(entry.getKey(), entry.getValue());
                    }
                }
                body = builder.build();
            }
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            HTTP_CLIENT.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    callBack.onFail();
                }

                @Override
                public void onResponse(Call call, Response response) {
                    try {
                        String value = response.body().string();
                        callBack.onSuccess(value);
                    } catch(Exception e) {
                        callBack.onFail();
                    }
                }
            });
        }
    }

    private static String getMimeType(String filename) {
        FileNameMap filenameMap = URLConnection.getFileNameMap();
        String contentType = filenameMap.getContentTypeFor(filename);
        if (contentType == null) {
            contentType = "application/octet-stream"; //* exe,所有的可执行程序
        }
        return contentType;
    }
}
