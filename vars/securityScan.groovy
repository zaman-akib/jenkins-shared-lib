#!/usr/bin/env groovy

def call(Map config) {
    // Build parameters for security_scan step
    echo "Starting Black Duck Security Scan from Shared Library"

    def stepParams = [
        product: "polaris",
        polaris_server_url: config.polaris_server_url,
        polaris_access_token: config.polaris_access_token,
        polaris_assessment_types: config.polaris_assessment_types,
        polaris_application_name: config.polaris_application_name,
        polaris_project_name: config.polaris_project_name,
        polaris_branch_name: config.polaris_branch_name
    ]

    // Execute the security_scan plugin step
    security_scan(stepParams)
}