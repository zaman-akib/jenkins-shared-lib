# Jenkins Shared Library for Security Scanning

This Jenkins shared library provides a simplified interface for executing Polaris security scans in your Jenkins pipelines.

## Usage

### 1. Configure the Shared Library in Jenkins

Add this repository as a shared library in your Jenkins instance:
- Go to "Manage Jenkins" > "Configure System"
- Under "Global Pipeline Libraries", add a new library
- Set the library name (e.g., "security-scan-lib")
- Point to this repository

### 2. Use in Your Pipeline

```groovy
@Library('security-scan-lib') _

pipeline {
    agent any
    
    stages {
        stage('Security Scan') {
            steps {
                securityScan([
                    polaris_server_url: "https://your-polaris-server.com",
                    polaris_access_token: "${POLARIS_TOKEN}",
                    polaris_assessment_types: "SCA, SAST",
                    polaris_application_name: "MyApp",
                    polaris_project_name: "MyProject",
                    polaris_branch_name: "${BRANCH_NAME}"
                ])
            }
        }
    }
}
```

### 3. Scripted Pipeline Usage

```groovy
@Library('security-scan-lib') _

node {
    stage('Security Scan') {
        securityScan([
            polaris_server_url: "https://your-polaris-server.com",
            polaris_access_token: "${POLARIS_TOKEN}",
            polaris_assessment_types: "SCA, SAST"
        ])
    }
}
```

## Parameters

### Required Parameters
- `polaris_server_url`: URL of your Polaris server
- `polaris_access_token`: Access token for Polaris authentication
- `polaris_assessment_types`: Types of assessments to run (e.g., "SCA", "SAST", "SCA, SAST")

### Optional Parameters
- `polaris_application_name`: Name of the application in Polaris
- `polaris_project_name`: Name of the project in Polaris
- `polaris_branch_name`: Branch name for the scan

## Additional Functions

### Unit Test
```groovy
unitTest()  // Outputs: "Unit test execution completed"
```

### Deploy
```groovy
deploy()  // Outputs: "Build deployed successfully"
```

### Using SecurityScanConfig Class

You can also use the `SecurityScanConfig` class for object-oriented parameter management:

```groovy
@Library('security-scan-lib') _

pipeline {
    agent any
    
    stages {
        stage('Security Scan') {
            steps {
                script {
                    def scanConfig = new SecurityScanConfig()
                        .setPolarisServerUrl("https://your-polaris-server.com")
                        .setPolarisAccessToken("${POLARIS_TOKEN}")
                        .setPolarisAssessmentTypes("SCA, SAST")
                        .setPolarisApplicationName("MyApp")
                        .setPolarisProjectName("MyProject")
                        .setPolarisBranchName("${BRANCH_NAME}")
                    
                    scanConfig.executeScan(this)
                }
            }
        }
        
        stage('Unit Test') {
            steps {
                unitTest()
            }
        }
        
        stage('Deploy') {
            steps {
                deploy()
            }
        }
    }
}
```

### Complete Pipeline Example

```groovy
@Library('security-scan-lib') _

pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
                echo "Building application..."
            }
        }
        
        stage('Unit Test') {
            steps {
                unitTest()
            }
        }
        
        stage('Security Scan') {
            steps {
                script {
                    def scanConfig = new SecurityScanConfig()
                        .setPolarisServerUrl("https://your-polaris-server.com")
                        .setPolarisAccessToken("${POLARIS_TOKEN}")
                        .setPolarisAssessmentTypes("SCA, SAST")
                        .setPolarisApplicationName("MyApp")
                        .setPolarisProjectName("MyProject")
                    
                    scanConfig.executeScan(this)
                }
            }
        }
        
        stage('Deploy') {
            steps {
                deploy()
            }
        }
    }
}
```

### Using SimplePipeline Class

For a more structured approach, you can use the `SimplePipeline` class:

```groovy
@Library('security-scan-lib') _

pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
                echo "Building application..."
            }
        }
        
        stage('Pipeline Execution') {
            steps {
                script {
                    // Create pipeline instance
                    SimplePipeline pipeline = new SimplePipeline(this)
                    
                    // Create security scan configuration
                    def scanConfig = new SecurityScanConfig()
                        .setPolarisServerUrl("https://your-polaris-server.com")
                        .setPolarisAccessToken("${POLARIS_TOKEN}")
                        .setPolarisAssessmentTypes("SCA, SAST")
                        .setPolarisApplicationName("MyApp")
                        .setPolarisProjectName("MyProject")
                    
                    // Execute pipeline steps
                    pipeline.executeUnitTest()
                    pipeline.executeSecurityScan(scanConfig)
                    pipeline.deploy()
                }
            }
        }
    }
}