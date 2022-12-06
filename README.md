# Getting Started

### Create k8s cluster locally with Docker and minikube

### Commands

- build a Docker image with Jib and push in the local register: `` ./gradlew jibDockerBuild ``
- load the image into Miikube with the command: `` minikube image load cart ``
- deploy the application in your local k8s, go to `/k8s-manifest` folder and run 
  - `` kubectl -f deployment.yaml ``
- check if everything is running with the command `` kubectl get all -n t19 ``. You should be able to see:
```
NAME                       READY   STATUS    RESTARTS   AGE
pod/cart-969c66977-7hfvt   2/2     Running   0          9m28s
pod/cart-969c66977-pvmtd   2/2     Running   0          9m29s

NAME           TYPE        CLUSTER-IP       EXTERNAL-IP   PORT(S)    AGE
service/cart   ClusterIP   10.100.201.143   <none>        8080/TCP   30s

NAME                   READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/cart   2/2     2            2           9m29s

NAME                             DESIRED   CURRENT   READY   AGE
replicaset.apps/cart-969c66977   2         2         2       9m29s
```

- expose the application outside with this command: ``` kubectl port-forward service/cart 8080:8080 -n t19-investigate-istio ```

- run this command if the service is reachable: ``curl http://localhost:8080/cart/1``. You should get an id as response, for example `"fb817981-ed77-4709-84e8-9bdcf83e2531"`

- configure the circuit breaker adding the k8s custom resources Destination rule running the command: `` kubectl apply -f destinationRule.yaml ``

- eject requests based by the throughput
  - ``export FORTIO_POD=$(kubectl get pods -l app=fortio -n t19 -o 'jsonpath={.items[0].metadata.name}')``
  - ``kubectl exec "$FORTIO_POD" -c fortio -n t19 -- /usr/bin/fortio load -c 2 -qps 0 -n 20 -loglevel Warning http://cart:8080/cart/1``
  
- eject request based by errors
- 
