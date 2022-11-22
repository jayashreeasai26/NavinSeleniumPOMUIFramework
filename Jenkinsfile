pipeline{
    
    agent any
    
    stages{
    
    stage("Build"){
        steps{
        echo("Build project")
        }
    }
    
    stage("Deploy to Dev"){
        steps{
        echo("deploy to Dev")
        }
    }
     
     stage("Run UAT's"){
         steps{
        echo("Run UT's")
         }
     }
    
    stage("Deploy to QA"){
        steps{
        echo("deploy to QA env")
        }
    }
    
     stage("Run Regression Automation Test"){
         steps{
        echo("run regression test cases")
         }
     }
    
     stage("Deploy to STAGE"){
         steps{
        echo("deploy to stage env")
         }
     }
    
    stage("Run Sanity Automation Test"){
        steps{
        echo("run sanity test cases")
        }
    }
    
    stage("Deploy to PROD"){
        steps{
        echo("deploy to PROD env")
        }
    }
    
    }
}
    
    
    
    
    
    
    
