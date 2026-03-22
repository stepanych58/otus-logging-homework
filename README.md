# otus-logging-homework

Пример интеграции ELK (Elasticsearch, Logstash, Kibana) с приложением в Kubernetes на Minikube.

Этот проект демонстрирует, как развернуть микросервис и стек ELK локально с помощью Minikube и Helm.

---

## Предварительные требования

Перед установкой убедитесь, что у вас установлены:

* [Minikube](https://minikube.sigs.k8s.io/docs/start/)
* [kubectl](https://kubernetes.io/docs/tasks/tools/)
* [Docker](https://www.docker.com/get-started)
* [Helm](https://helm.sh/docs/intro/install/)

Проверить установленные версии можно командой:

```bash
minikube version
kubectl version --client
docker version
helm version
```

---

## Запуск Minikube

1. Запустите Minikube с драйвером Docker:

```bash
minikube start --driver=docker
```

2. Откройте дашборд Kubernetes:

```bash
minikube dashboard
```

3. Включите аддон Ingress:

```bash
minikube addons enable ingress
```

4. Создайте туннель для внешнего доступа:

```bash
minikube tunnel
```

5. Подождите, пока запустятся все pod’ы NGINX Ingress Controller:

```bash
kubectl get pods -n ingress-nginx
```

---

## Развёртывание приложений

### 1. Установка микросервиса `userservice`

```bash
helm install userservice ./helm/user-service-chart -n otus-msa --create-namespace
```
#### Выполните проверку доступности приложения http://arch.homework/otus-user-service/swagger-ui/index.html

### 2. Установка ELK стека

```bash
helm install elk ./helm/elk-chart -n logging --create-namespace
```

Подождите, пока запустятся все pod’ы ELK:

```bash
kubectl get pods -n logging
```

---

## Доступ к сервисам

### Elasticsearch

Для проверки работы Elasticsearch выполните порт-форвард:

```bash
kubectl port-forward svc/elasticsearch 9200:9200 -n logging
```

Теперь Elasticsearch доступен по адресу: `http://localhost:9200`.

### Kibana

Для доступа к Kibana используйте команду:

```bash
minikube service kibana -n logging
```

---

## Удаление приложений

Для удаления приложений по namespace используйте:

```bash
helm uninstall userservice -n otus-msa
helm uninstall elk -n logging

kubectl delete namespace otus-msa logging
```

---

## Полезные команды

* Проверить состояние pod’ов:

```bash
kubectl get pods -n otus-msa
kubectl get pods -n logging
```

* Просмотр логов pod’а:

```bash
kubectl logs <pod-name> -n <namespace>
```
* Рестарт демонсета

```bash
kubectl rollout restart daemonset <daemonset-name> -n <namespace>
```
kubectl rollout restart daemonset fluent-bit