#!/bin/bash

# Ir al directorio raíz del proyecto
cd "$(dirname "$0")"

# Directorio fuente y de destino
SRC_DIR="src"
OUT_DIR="WEB-INF/classes"
servlet_api="/c/Program Files/Apache Software Foundation/Tomcat 9.0_Tomcat9-2/lib/servlet-api.jar"

# Crear el directorio de salida si no existe
mkdir -p "$OUT_DIR"

# Buscar y compilar todos los .java desde src/, generando .class en WEB-INF/classes
find "$SRC_DIR" -name "*.java" > sources.txt
javac -d "$OUT_DIR" -classpath "$servlet_api" @sources.txt

# Limpieza opcional
rm sources.txt

echo "Compilación completada. Los .class están en $OUT_DIR"
