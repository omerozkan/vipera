/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package info.ozkan.vipera.client;

import info.ozkan.vipera.api.healthdata.HealthDataModel;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import com.google.gson.Gson;

/**
 * Vipera'ya sağlık verisi ekler
 * 
 * @author Ömer Özkan
 */
public class HealthDataAddClient {
    /**
     * Sayfa bulunamadı
     */
    public static final String NOT_FOUND = "NOT_FOUND";
    /**
     * sunucu hatası
     */
    public static final String SERVER_ERROR = "SERVER_ERROR";
    /**
     * yetkilendirme hatası
     */
    public static final String FORBIDDEN = "FORBIDDEN";
    /**
     * yetkilendirme hatası
     */
    public static final String AUTHORIZATION_FAILED = "AUTHORIZATION_FAILED";
    /**
     * bad request
     */
    public static final String BAD_REQUEST = "BAD_REQUEST";
    /**
     * başarılı
     */
    public static final String SUCCESS = "SUCCESS";
    /**
     * gson
     */
    public final Gson gson = new Gson();
    /**
     * vipera adresi
     */
    public final String viperaUrl;

    /**
     * consructor
     * 
     * @param url
     */
    public HealthDataAddClient(final String url) {
        viperaUrl = url;
    }

    /**
     * ekleme işlemi
     * 
     * @param model
     * @return
     */
    public String add(final HealthDataModel model) {
        String result = "";
        try {
            final String url = viperaUrl + "/rest/healthdata/add";
            final String json = gson.toJson(model);
            final Request request =
                    Request.Post(url).bodyString(json,
                            ContentType.APPLICATION_JSON);
            request.execute().returnContent();
            result = SUCCESS;
        } catch (final ClientProtocolException e) {
            final String message = e.getMessage();
            if (message.contains("400")) {
                result = BAD_REQUEST;
            } else if (message.contains("401")) {
                result = AUTHORIZATION_FAILED;
            } else if (message.contains("Forbidden")) {
                result = FORBIDDEN;
            } else if (message.contains("500")) {
                result = SERVER_ERROR;
            } else {
                result = NOT_FOUND;
            }
            e.printStackTrace();
        } catch (final IOException e) {
            result = NOT_FOUND;
            e.printStackTrace();
        }
        return result;
    }
}
