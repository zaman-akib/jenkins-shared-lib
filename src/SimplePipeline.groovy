class SimplePipeline implements Serializable {
    def script
    
    SimplePipeline(script) {
        this.script = script
    }
    
    void executeUnitTest() {
        script.unitTest()
    }
    
    void executeSecurityScan(SecurityScanConfig scanConfig) {
        scanConfig.executeScan(script)
    }
    
    void deploy() {
        script.deploy()
    }
}