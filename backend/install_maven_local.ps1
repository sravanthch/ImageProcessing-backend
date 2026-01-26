$mavVersion = "3.9.6"
$mavUrl = "https://dlcdn.apache.org/maven/maven-3/$mavVersion/binaries/apache-maven-$mavVersion-bin.zip"
$output = "maven.zip"
$destination = "maven"

Write-Host "Downloading Maven from $mavUrl..."
Invoke-WebRequest -Uri $mavUrl -OutFile $output

Write-Host "Extracting Maven..."
Expand-Archive -Path $output -DestinationPath $destination -Force

Write-Host "Maven installed to $destination/apache-maven-$mavVersion"
Write-Host "You can now run: $destination/apache-maven-$mavVersion/bin/mvn spring-boot:run"
