package com.uw.db.util;

public enum UWStatus {
        INIT("init"), IN_PROGRESS("in progress"), ON_HOLD("on hold"), FAILED("failed"), COMPLETED("done");
        private String status;

        private UWStatus(String status) {
            this.status=status;
        }

        public String getStatus() {
            return status;
        }
    }