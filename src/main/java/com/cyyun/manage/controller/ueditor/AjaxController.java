package com.cyyun.manage.controller.ueditor;

public class AjaxController {

    public final static class AjaxResponse {
        // 成功信息
        private String ok;

        // 失败信息
        private String error;

        private AjaxResponse(boolean isSuccess, String message) {
            if (isSuccess) {
                ok = message;
            } else {
                error = message;
            }
        }

        public static AjaxResponse getResponse(boolean isSuccess, String message) {
            return new AjaxResponse(isSuccess, message);
        }

        public String getOk() {
            return ok;
        }

        public void setOk(String ok) {
            this.ok = ok;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

    }

    public final static class UploadResponse {
        private String originalName;

        private String url;

        private String title;

        private String state;

        public UploadResponse(String originalName, String url, String title,
                String state) {
            super();
            this.originalName = originalName;
            this.url = url;
            this.title = title;
            this.state = state;
        }

        public UploadResponse() {

        }

        public String getOriginalName() {
            return originalName;
        }

        public void setOriginalName(String originalName) {
            this.originalName = originalName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}
