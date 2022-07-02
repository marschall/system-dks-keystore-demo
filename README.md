System DKS Keystore Demo
=======================--

Demo project for combining [marschall/system-dks-keystore](https://github.com/marschall/system-dks-keystore) with [marschall/directory-keystore](https://github.com/marschall/directory-keystore). The Directory Keystore library allows you to use the system certificates of a Linux distrubution (eg. `/etc/pki/ca-trust/source/anchors`) in combination with the certificates built in Java as a truststore for Java applications without any code changes. To make this work you only need six things:

1. The libraries itself which are two JARs without any dependencies, they run on Java 8 and Java 11. They need to the on the classpath or the modulepath.
1. A file containing the location of the directory where the certificates are located. For example `/etc/pki/ca-trust/source/anchors`.
1. A [keystore domain file](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/security/DomainLoadStoreParameter.html). For example [red-hat.dks](src/test/resources/red-hat.dks).
1. A another file linking to the keystore domain file and domain to be used. For example [red-hat.sdks](src/test/resources/red-hat.sdks).
1. A properties file to append the providers to the list of the installed providers.
1. Three additional JVM options . These are:
   1. `java.security.properties` this file is used to add the security providers to the exising providers. Pay attention to use only a single `=`.
   1. `javax.net.ssl.trustStore` this file contains the location of the of the keystore domain file and domain to be used. This indirection is necessary due to the way Java loads keystores.
   1. `javax.net.ssl.trustStoreType` this must have the value `system-DKS`

Putting this all togehter you JVM options should something like this:

```
-Djava.security.properties=${basedir}/src/test/resources/additional.java.security \
-Djavax.net.ssl.trustStore=${basedir}/src/test/resources/red-hat.sdks \
-Djavax.net.ssl.trustStoreType=system-DKS
```

The code should work with any JVM.

