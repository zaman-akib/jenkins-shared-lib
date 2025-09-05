class SecurityScanConfig implements Serializable {
    String polarisServerUrl
    String polarisAccessToken
    String polarisAssessmentTypes
    String polarisApplicationName
    String polarisProjectName
    String polarisBranchName
    
    SecurityScanConfig() {
        // Default constructor
    }
    
    SecurityScanConfig setPolarisServerUrl(String url) {
        this.polarisServerUrl = url
        return this
    }
    
    SecurityScanConfig setPolarisAccessToken(String token) {
        this.polarisAccessToken = token
        return this
    }
    
    SecurityScanConfig setPolarisAssessmentTypes(String types) {
        this.polarisAssessmentTypes = types
        return this
    }
    
    SecurityScanConfig setPolarisApplicationName(String name) {
        this.polarisApplicationName = name
        return this
    }
    
    SecurityScanConfig setPolarisProjectName(String name) {
        this.polarisProjectName = name
        return this
    }
    
    SecurityScanConfig setPolarisBranchName(String name) {
        this.polarisBranchName = name
        return this
    }
    
    Map toMap() {
        Map config = [:]
        if (polarisServerUrl) config.polaris_server_url = polarisServerUrl
        if (polarisAccessToken) config.polaris_access_token = polarisAccessToken
        if (polarisAssessmentTypes) config.polaris_assessment_types = polarisAssessmentTypes
        if (polarisApplicationName) config.polaris_application_name = polarisApplicationName
        if (polarisProjectName) config.polaris_project_name = polarisProjectName
        if (polarisBranchName) config.polaris_branch_name = polarisBranchName
        return config
    }
    
    void executeScan(script) {
        script.securityScan(this.toMap())
    }
}