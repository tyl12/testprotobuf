protoc -I=./ --cpp_out=./  infor.proto
proto  --java_out=./ infor.proto

g++ server.cpp infor.pb.cc `pkg-config --libs protobuf`
./a.out &

cd maven/myproj
mvn compile
mvn package
cd target/
java -jar *.jar

