package com.benznestdeveloper.blognonestory.firebase;

import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by benznest on 20-Mar-18.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        // When token expired token will refresh to get new token.
    }
}