job('NodeJS Docker example') {
    scm {
        git('https://github.com/vipul71098/nodejs-jenkins.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('vipul71098')
            node / gitConfigEmail('vipul71098@gmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('vipulshukla71098/node-demo')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
