# TLS Decryption Demo with jSSLKeyLog

This project contains a Server (The Vault) and a Client (The Fetcher) to demonstrate TLS key extraction.

## Prerequisites
1. Download `jSSLKeyLog.jar` (e.g., from SourceForge or GitHub).
2. Generate a self-signed certificate for the server:
   keytool -genkeypair -alias spring-boot -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore keystore.p12 -validity 3650 -storepass password

3. Place `keystore.p12` into:
   - `the-vault-server/src/main/resources/`

## Running the Demo

### 1. Start the Server
cd the-vault-server
mvn spring-boot:run

### 2. Start the Client with jSSLKeyLog
cd fetcher-client
mvn clean package
java -javaagent:../jSSLKeyLog.jar==../tls-keys.log \
     -Djavax.net.ssl.trustStore=../the-vault-server/src/main/resources/keystore.p12 \
     -Djavax.net.ssl.trustStorePassword=password \
     -jar target/fetcher-client-0.0.1-SNAPSHOT.jar

### 3. Open Wireshark
- Capture on 'loopback' (localhost).
- Filter: `tcp.port == 8443`.
- Go to Preferences -> Protocols -> TLS -> (Pre)-Master-Secret log filename.
- Select the generated `tls-keys.log`.
