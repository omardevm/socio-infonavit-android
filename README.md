


# SocioInfonavit App

This is my Android Application for the company **Nextia**. Hope you enjoy it.

## Installation
You can clone this repo inside **Android Studio**.
```bash
git clone https://github.com/omardevm/socio-infonavit-android
```
You can download my apk file here:
```bash
https://drive.google.com/file/d/1WJyz9oB1eqi45WzvNCd6HaFi3HcU80w5/view?usp=sharing
```
## Main Activity
![alt text](https://i.imgur.com/9zXD86A.jpg)
Módulo de carga con Animación fade-in del logo de socioinfonavit.
Botón deshabilitado hasta que se ingresan correo y contraseña.
Llamada POST al endpoint de login.
Diálogo para notificación intento fallido de sesión.
Módulo de persistencia de sesión y obtención de Token JWT

## Benevits
![alt text](https://i.imgur.com/LGiuRA1.jpg)
![alt text](https://i.imgur.com/RL0a3lk.jpg)
Skeleton de carga con Shimmer de los datos entrantes
Llamada a los dos enpoints, uno para los wallets, el segundo para los benevits.
Segmented Recyclerview nativo para el mapeo de cada tipo de cartera y para
las carteras.
Item para determinar si el benevit es de tipo unlock / lock.

## Logout
![alt text](https://i.imgur.com/isPZf2Z.jpg)
Diálogo para notificar si se desea el cierre de sesión.
Llamada al endpoint DELETE.
Destrucción de los datos en el módulo de persistencia.
Regreso satisfactorio al inicio de sesión.