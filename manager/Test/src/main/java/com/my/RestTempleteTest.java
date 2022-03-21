package com.my;

import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

/**
 * 描 述: <br/>
 * 作 者: liuliang14<br/>
 * 创 建:2022年01月12⽇<br/>
 * 版 本:v1.0.0<br> *
 * 历 史: (版本) 作者 时间 注释 <br/>
 */
public class RestTempleteTest {
    @Test
    public void test() throws Exception {
//SimpleClientHttpRequest
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        RestTemplate template = new RestTemplate();
        template.setRequestFactory(factory);
        String url = "http://contents.guazistatic.com/baichuan/video/26ed94c3-f671-4a60-9bc1-19afe55c1d8b.mp4@base@video@tag=frame&format=jpg&ss=17&width=100&height=100&rotate=90";
        byte[] forObject = template.getForObject(url, byte[].class);
        File file = new File("/Users/ll/44.jpg");


        FileOutputStream out = new FileOutputStream(file);
        out.write(forObject);
        out.close();


        int a = 0;
    }

    @Test
    public void test2() throws Exception {
        //SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        //RestTemplate template = new RestTemplate();
        //template.setRequestFactory(factory);
        //byte[] bytes = "aa".getBytes();
        //HttpEntity<byte[]> entity = new HttpEntity<>(bytes);
        //template.postForEntity("http://example.com/myFileUpload", entity, String.class);

        byte[] getimage = getimage();

        String url = "https://bjguazi01.ks3-cn-beijing.ksyun.com";
        //RestTemplate client = new RestTemplate();
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        RestTemplate template = new RestTemplate();
        template.setRequestFactory(factory);
        HttpHeaders headers = new HttpHeaders();
//  请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//  封装参数，千万不要替换为Map与HashMap，否则参数无法传递
        MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();
//  也支持中文
        params.add("acl","public-read");
        params.add("KSSAccessKeyId", "AS1kwcmTYltOcoqibqSj");
        params.add("Policy", "eyJleHBpcmF0aW9uIjoiMjAyMi0wMS0xMlQxMzozNzoxMC41OTIwMFoiLCJjb25kaXRpb25zIjpbWyJlcSIsIiRhY2wiLCJwdWJsaWMtcmVhZCJdLFsiZXEiLCIkYnVja2V0IiwiYmpndWF6aTAxIl0sWyJlcSIsIiRrZXkiLCJnejAxMjIwMTEyLzIxLzI3LzVmNjRjMWE2ODA1NThmYzcwYjFiZDM5NjIxOGQ2OGIzLnBuZyJdXX0=");

        params.add("key","gz01220112/21/27/5f64c1a680558fc70b1bd396218d68b3.png");
        //params.add("file","2222");
        FileSystemResource resource = new FileSystemResource("/Users/ll/44.jpg");
        params.add("file",resource);
        //params.add("file",getimage);
        params.add("signature","fiiUfqfbgtsvK2FfOiNyZzrLpuo=");

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(params, headers);
//  执行HTTP请求
        ResponseEntity<String> response = template.exchange(url, HttpMethod.POST, requestEntity, String.class);
//  输出结果
        System.out.println(response.getBody());


        int a = 0;
    }

    @Test
    public void uploadFile()throws Exception{

        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(factory);
        String url = "https://bjguazi01.ks3-cn-beijing.ksyun.com";//clientProperties.getApiUrl(URL_USER_UPLOAD);
        HttpHeaders headers = new HttpHeaders();


        MediaType type = MediaType.parseMediaType("multipart/form-data");
        headers.setContentType(type);
        String cd = "filename=\"" + "sss"+ "\"";
        headers.add("Content-Disposition", cd);
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<String, Object>();
        form.add("acl","public-read");
        form.add("KSSAccessKeyId", "AS1kwcmTYltOcoqibqSj");
        form.add("Policy", "eyJleHBpcmF0aW9uIjoiMjAyMi0wMS0xMlQxMzozNzoxMC41OTIwMFoiLCJjb25kaXRpb25zIjpbWyJlcSIsIiRhY2wiLCJwdWJsaWMtcmVhZCJdLFsiZXEiLCIkYnVja2V0IiwiYmpndWF6aTAxIl0sWyJlcSIsIiRrZXkiLCJnejAxMjIwMTEyLzIxLzI3LzVmNjRjMWE2ODA1NThmYzcwYjFiZDM5NjIxOGQ2OGIzLnBuZyJdXX0=");

        form.add("key","gz01220112/21/27/5f64c1a680558fc70b1bd396218d68b3.png");
        form.add("signature","fiiUfqfbgtsvK2FfOiNyZzrLpuo=");
        byte[] getimage = getimage();
        form.add("file", getimage);
        HttpEntity<MultiValueMap<String, Object>> files = new HttpEntity<>(form, headers);
        ResponseEntity<String> responseEntity = null;
        responseEntity = restTemplate.postForEntity(url, files, String.class);

        String body = responseEntity.getBody();
        System.out.println(body);
    }


    private byte[] getimage(){

        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        RestTemplate template = new RestTemplate();
        template.setRequestFactory(factory);
        String url = "http://contents.guazistatic.com/baichuan/video/26ed94c3-f671-4a60-9bc1-19afe55c1d8b.mp4@base@video@tag=frame&format=jpg&ss=177&width=100&height=100&rotate=90";
        byte[] forObject = template.getForObject(url, byte[].class);
        return forObject;
    }
}
