package com.sogeti.jenkins;

import java.io.FileOutputStream;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class ConfigXml {
	
	//Nous allons commencer notre arborescence en créant la racine XML
	   //qui sera ici "personnes".
	   static Element racine = new Element("maven2-moduleset");
	   Attribute plugin = new Attribute("plugin","maven-plugin@2.7.1"); 
	   

	   //On crée un nouveau Document JDOM basé sur la racine que l'on vient de créer
	   static org.jdom.Document document = new Document(racine);

	   public static void main(String[] args) {
		   
		   Element description = new Element("description");
		   description.setText("Back du projet");
		   racine.addContent(description);
		   
		   Element keepDependencies = new Element("keepDependencies");
		   keepDependencies.setText("false");
		   racine.addContent(keepDependencies);
		   
	      //On crée un nouvel Element scm et on l'ajoute
	      //en tant qu'Element de racine
	      Element scm = new Element("scm");
	      racine.addContent(scm);

	      //On crée un nouvel Attribut classe et on l'ajoute à etudiant
	      //grâce à la méthode setAttribute
	      Attribute classe = new Attribute("classe","hudson.plugins.git.GitSCM");
	      Attribute plugin = new Attribute("plugin","git@2.4.4");
	      scm.setAttribute(classe);
	      scm.setAttribute(plugin);

	      //On crée un nouvel Element configVersion, on lui assigne du texte
	      //et on l'ajoute en tant qu'Element de scm
	      Element configVersion = new Element("configVersion");
	      configVersion.setText("2");
	      scm.addContent(configVersion);
	      
	      //On crée un deuxieme Element 
	      Element userRemoteConfigs = new Element("userRemoteConfigs");
	      Element hudsonpluginsgitUserRemoteConfig = new Element("hudson.plugins.git.UserRemoteConfig");
	      
	      //On cree un 3eme element 
	      Element url = new Element("url");
	      url.setText("https://bitbucket.org/picsogeti/pic_bo.git");
	     
	      //On cree un 4eme element
	      Element credentialsId = new Element("credentialsId");
	      credentialsId.setText("014023ec-c2b2-4d8b-a56e-ad093a74495d");
	      
	      hudsonpluginsgitUserRemoteConfig.addContent(url);
	      hudsonpluginsgitUserRemoteConfig.addContent(credentialsId);
	      userRemoteConfigs.addContent(hudsonpluginsgitUserRemoteConfig);
	      scm.addContent(userRemoteConfigs);
	      
	      //On cree la branche dans scm
	      Element branche = new Element("branche");
	      Element hudsonpluginsgitBranchSpec = new Element("hudson.plugins.git.BranchSpec");
	      Element name = new Element("name");
	      name.setText("*/master");
	      hudsonpluginsgitBranchSpec.addContent(name);
	      branche.addContent(hudsonpluginsgitBranchSpec);
	      scm.addContent(branche);
	      //On cree doGenerateSubmoduleConfigurations dans scm
	      Element doGenerateSubmoduleConfigurations = new Element("doGenerateSubmoduleConfigurations");
	      doGenerateSubmoduleConfigurations.setText("false");
	      scm.addContent(doGenerateSubmoduleConfigurations);
	      //On cree submoduleCfg dans scm
	      Element submoduleCfg = new Element("submoduleCfg");
	      Attribute classe1 = new Attribute("classe","list");
	      submoduleCfg.setAttribute(classe1);
	      scm.addContent(submoduleCfg);
	      //On cree extensions dans scm
	      Element extensions = new Element("extensions");
	      scm.addContent(extensions);
	      
	      //On cree canRoam
	      Element canRoam = new Element("canRoam");
	      canRoam.setText("true");
	      racine.addContent(canRoam);
	      
	      //On cree disabled
	      Element disabled = new Element("disabled");
	      disabled.setText("false");
	      racine.addContent(disabled);
	      
	      //On cree blockBuildWhenDownstreamBuilding
	      Element blockBuildWhenDownstreamBuilding = new Element("blockBuildWhenDownstreamBuilding");
	      blockBuildWhenDownstreamBuilding.setText("true");
	      racine.addContent(blockBuildWhenDownstreamBuilding);
	      
	      //On cree blockBuildWhenUpstreamBuilding
	      Element blockBuildWhenUpstreamBuilding = new Element("blockBuildWhenUpstreamBuilding");
	      blockBuildWhenUpstreamBuilding.setText("true");
	      racine.addContent(blockBuildWhenUpstreamBuilding);
	      
	      //On cree triggers
	      Element triggers = new Element("triggers");
	      Element hudsontriggersSCMTrigger = new Element("hudson.triggers.SCMTrigger");
	      Element spec = new Element("spec");
	      spec.setText("* 8-19 * * 1-5 ");
	      hudsontriggersSCMTrigger.addContent(spec);
	      Element ignorePostCommitHooks = new Element("ignorePostCommitHooks");
	      ignorePostCommitHooks.setText("false");
	      hudsontriggersSCMTrigger.addContent(ignorePostCommitHooks);
	      triggers.addContent(hudsontriggersSCMTrigger);
	      racine.addContent(triggers);
	      
	      //On cree concurrentBuild
	      Element concurrentBuild = new Element("concurrentBuild");
	      concurrentBuild.setText("false");
	      racine.addContent(concurrentBuild);
	      
	      //On cree rootModule
	      Element rootModule = new Element("rootModule");
	      Element groupId = new Element("groupId");
	      groupId.setText("com.sogeti");
	      rootModule.addContent(groupId);
	      Element artifactId = new Element("artifactId");
	      artifactId.setText("PIC_BO");
	      rootModule.addContent(artifactId);
	      racine.addContent(rootModule);
	      
	      //On cree goals
	      Element goals = new Element("goals");
	      goals.setText("clean install");
	      racine.addContent(goals);
	      
	      //On cree aggregatorStyleBuild
	      Element aggregatorStyleBuild = new Element("aggregatorStyleBuild");
	      aggregatorStyleBuild.setText("true");
	      racine.addContent(aggregatorStyleBuild);
	      
	      //On cree incrementalBuild
	      Element incrementalBuild = new Element("incrementalBuild");
	      incrementalBuild.setText("false");
	      racine.addContent(incrementalBuild);
	      
	      //On cree ignoreUpstremChanges
	      Element ignoreUpstremChanges = new Element("ignoreUpstremChanges");
	      ignoreUpstremChanges.setText("false");
	      racine.addContent(ignoreUpstremChanges);
	      
	      //On cree archivingDisabled
	      Element archivingDisabled = new Element("archivingDisabled");
	      archivingDisabled.setText("false");
	      racine.addContent(archivingDisabled);
	      
	      //On cree archivingDisabled
	      Element siteArchivingDisabled = new Element("siteArchivingDisabled");
	      siteArchivingDisabled.setText("false");
	      racine.addContent(siteArchivingDisabled);
	      
	      //On cree fingerprintingDisabled
	      Element fingerprintingDisabled = new Element("fingerprintingDisabled");
	      fingerprintingDisabled.setText("false");
	      racine.addContent(fingerprintingDisabled);
	      
	      //On cree archivingDisabled
	      Element resolveDependencies = new Element("resolveDependencies");
	      resolveDependencies.setText("false");
	      racine.addContent(resolveDependencies);
	      
	      //On cree processPlugins
	      Element processPlugins = new Element("processPlugins");
	      processPlugins.setText("false");
	      racine.addContent(processPlugins);
	      
	     //On cree archivingDisabled
	      Element mavenValidationLevel = new Element("mavenValidationLevel");
	      mavenValidationLevel.setText("1");
	      racine.addContent(mavenValidationLevel);
	      
	      //On cree runHeadless
	      Element runHeadless = new Element("runHeadless");
	      runHeadless.setText("false");
	      racine.addContent(runHeadless);
	      
	      //On cree disableTriggerDownstreamProjects
	      Element disableTriggerDownstreamProjects = new Element("disableTriggerDownstreamProjects");
	      disableTriggerDownstreamProjects.setText("false");
	      racine.addContent(disableTriggerDownstreamProjects);
	      
	      //On cree blockTriggerWhenBuilding
	      Element blockTriggerWhenBuilding = new Element("blockTriggerWhenBuilding");
	      blockTriggerWhenBuilding.setText("true");
	      racine.addContent(blockTriggerWhenBuilding);
	      
	      //On cree settings
	      Element settings = new Element("settings");
	      Attribute classe2 = new Attribute("classe","jenkins.mvn.DefaultSettingsProvider");
	      settings.setAttribute(classe2);
	      racine.addContent(settings);
	      
	      //On cree runHeadless
	      Element globalSettings = new Element("globalSettings");
	      Attribute classe3 = new Attribute("classe","jenkins.mvn.DefaultGlobalSettingsProvider");
	      globalSettings.setAttribute(classe3);
	      racine.addContent(globalSettings);
	      
	      //On cree reporters
	      Element reporters = new Element("reporters");
	      racine.addContent(reporters);
	      
	      //On cree publishers
	      Element publishers = new Element("publishers");
	      Element hudsonpluginsdeployDeployPublisher = new Element("hudson.plugins.deploy.DeployPublisher");
	      Attribute plugin2 = new Attribute("plugin","deploy@1.10");
	      hudsonpluginsdeployDeployPublisher.setAttribute(plugin2);
	      //Element adapters
	      Element adapters = new Element("adapters");
	      
	      //Element hudsonpluginsdeploytomcatTomcat7xAdapter
	      Element hudsonpluginsdeploytomcatTomcat7xAdapter = new Element("hudson.plugins.deploy.tomcat.Tomcat7xAdapter");
	      Element userName = new Element("userName");
	      userName.setText("tomcat");
	      hudsonpluginsdeploytomcatTomcat7xAdapter.addContent(userName);
	      
	      //Element passwordScrambled
	      Element passwordScrambled = new Element("passwordScrambled");
	      passwordScrambled.setText("dG9tY2F0");
	      hudsonpluginsdeploytomcatTomcat7xAdapter.addContent(passwordScrambled);
	      
	      //Element url2
	      Element url2 = new Element("url");
	      url2.setText("http://localhost:8082/");
	      hudsonpluginsdeploytomcatTomcat7xAdapter.addContent(url2);
	      hudsonpluginsdeployDeployPublisher.addContent(adapters);
	      
	      //Element contextPath
	      Element contextPath = new Element("contextPath");
	      contextPath.setText("boPIC");
	      hudsonpluginsdeployDeployPublisher.addContent(contextPath);
	     
	      //Element war
	      Element war = new Element("war");
	      war.setText("**/*.war");
	      hudsonpluginsdeployDeployPublisher.addContent(war);
	      
	      //Element onFailure
	      Element onFailure = new Element("onFailure");
	      onFailure.setText("false");
	      hudsonpluginsdeployDeployPublisher.addContent(onFailure);
	      publishers.addContent(hudsonpluginsdeployDeployPublisher);
	      racine.addContent(publishers);
	      
	      
	      
	      
	    

	      //Les deux méthodes qui suivent seront définies plus loin dans l'article
	      affiche();
	      enregistre("Exercice1.xml");
	      
	   }
	   
	   //Ajouter ces deux méthodes à notre classe JDOM1
	   static void affiche()
	   {
	      try
	      {
	         //On utilise ici un affichage classique avec getPrettyFormat()
	         XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
	         sortie.output(document, System.out);
	      }
	      catch (java.io.IOException e){}
	   }

	   static void enregistre(String fichier)
	   {
	      try
	      {
	         //On utilise ici un affichage classique avec getPrettyFormat()
	         XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
	         //Remarquez qu'il suffit simplement de créer une instance de FileOutputStream
	         //avec en argument le nom du fichier pour effectuer la sérialisation.
	         sortie.output(document, new FileOutputStream(fichier));
	      }
	      catch (java.io.IOException e){}
	   }
}
