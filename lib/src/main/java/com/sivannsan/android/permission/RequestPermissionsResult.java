package com.sivannsan.android.permission;

import com.sivannsan.foundation.annotation.Nonnull;

public class RequestPermissionsResult {
    private final int requestCode;
    @Nonnull
    private final String[] permissions;
    @Nonnull
    private final int[] grantResult;

    public RequestPermissionsResult(int requestCode, String[] permissions, int[] grantResult) {
        this.requestCode = requestCode;
        this.permissions = permissions;
        this.grantResult = grantResult;
    }

    public int getRequestCode() {
        return requestCode;
    }

    @Nonnull
    public String[] getPermissions() {
        return permissions;
    }

    @Nonnull
    public int[] getGrantResult() {
        return grantResult;
    }
}
