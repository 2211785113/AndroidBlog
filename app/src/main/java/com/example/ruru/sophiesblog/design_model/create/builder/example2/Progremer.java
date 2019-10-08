package com.example.ruru.sophiesblog.design_model.create.builder.example2;


public class Progremer extends TechManager {
    private Product product;
    private InnerProduct innerProduct = new InnerProduct();

    @Override
    public TechManager setAppName(String appName) {
        innerProduct.setAppName(appName);
        return this;
    }

    @Override
    public TechManager setAppFuction(String appFuction) {
        innerProduct.setAppFuction(appFuction);
        return this;
    }

    @Override
    public TechManager setAppSystem(int appSystem) {
        innerProduct.setAppSystem(appSystem);
        return this;
    }

    private class InnerProduct {
        private String appName;
        private String appFuction;
        private int appSystem;

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        public String getAppFuction() {
            return appFuction;
        }

        public void setAppFuction(String appFuction) {
            this.appFuction = appFuction;
        }

        public int getAppSystem() {
            return appSystem;
        }

        public void setAppSystem(int appSystem) {
            this.appSystem = appSystem;
        }
    }

    @Override
    public Product build() {
        product = new Product();
        product.setAppName(innerProduct.getAppName());
        product.setAppFuction(innerProduct.getAppFuction());
        product.setAppSystem(innerProduct.getAppSystem());
        return product;
    }
}
