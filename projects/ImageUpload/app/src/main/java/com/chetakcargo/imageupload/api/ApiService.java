package com.chetakcargo.imageupload.api;

import com.chetakcargo.imageupload.model.ImageResponseModel;
import com.chetakcargo.imageupload.utils.GlobalConstant;
import com.squareup.okhttp.RequestBody;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Ravinder Birhman on 05,June,2022
 * Country India,
 * State Haryana,
 * Email ravinder.birhman2000@gmail.com
 **/
public interface ApiService {

    @Multipart
    @POST(GlobalConstant.Url.API_PATH + "image/upload")
    Single<ImageResponseModel> uploadFile(@Part MultipartBody.Part image);
}
