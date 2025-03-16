#!/bin/sh
minikube start
# останавливаем существующие поды приложения, чтобы впоследствии закешировать актуальный образ
kubectl --namespace default scale deployment my-app-service-deployment --replicas 0
# приостанавливаем выполнение скрипта на 10 секунд, чтобы пода успела удалиться
sleep 15
# удаляем закешированный в миникубе образ приложения (my-app-service)
# если на этом шаге в логах видите ошибку, попробуйте увеличить время на предыдущем шаге
minikube image rm my-app-service
# загружаем актуальный образ приложения в кеш миникуба из локального репозитория (my-app-service)
minikube image load my-app-service
# запускаем поду приложения с новым образом
kubectl --namespace default scale deployment my-app-service-deployment --replicas 1
minikube dashboard