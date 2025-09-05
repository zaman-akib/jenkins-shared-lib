#!/usr/bin/env groovy

def call(Map config) {
    // Validate required parameters
    if (!config.polaris_server_url) {
        error("Parameter 'polaris_server_url' is required")
    }
    if (!config.polaris_access_token) {
        error("Parameter 'polaris_access_token' is required")
    }
    if (!config.polaris_assessment_types) {
        error("Parameter 'polaris_assessment_types' is required")
    }
    
    // Build parameters for security_scan step
    def stepParams = [
        product: "polaris",
        polaris_server_url: config.polaris_server_url,
        polaris_access_token: config.polaris_access_token,
        polaris_assessment_types: config.polaris_assessment_types
    ]
    
    // Add optional parameters if provided
    if (config.polaris_application_name) {
        stepParams.polaris_application_name = config.polaris_application_name
    }
    if (config.polaris_project_name) {
        stepParams.polaris_project_name = config.polaris_project_name
    }
    if (config.polaris_branch_name) {
        stepParams.polaris_branch_name = config.polaris_branch_name
    }
    
    // Execute the security_scan plugin step
    security_scan(stepParams)
}