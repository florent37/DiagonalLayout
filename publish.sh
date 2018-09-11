. ~/.bash_profile
./gradlew clean
./gradlew :diagonallayout:assembleDebug
./gradlew :diagonallayout:install
./gradlew :diagonallayout:bintrayUpload