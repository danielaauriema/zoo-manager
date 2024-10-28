 #!/bin/bash

docker build -t zooapp:latest .

docker run -it zooapp:latest