# Microplastics

## Resumen
La presencia de microplásticos en el mar es un problema que ha adquirido relevancia en los últimos tiempos. Para analizar su evolución se llevan los residuos plásticos de zonas costeras a laboratorios para su conteo exhaustivo. Debido a la complejidad de recopilación de muestras y tratamiento de datos es fundamental tanto la eficiencia como la estandarización durante el proceso.
    
El objetivo de este estudio es determinar la integración de herramientas automáticas, permitiendo acelerar su observación. Para ello se utilizaron imágenes capturadas directamente en la playa. 
    
En el documento se comparan los resultados obtenidos tanto en la detección como en la estimación de plásticos, entre distintas arquitecturas de redes neuronales artificiales.

## Abstract
Recently, the presence of micro-plastics at sea is an issue that has gained prominence. To analyze its evolution, plastic waste from seaside is taken to laboratories for its exhaustive counting. Due to the complexity of sample collection and data processing, efficiency and  standardization are essential during the process.

The objective of this study is to determine the integration of automatic tools to accelerate its analysis. For this, images captured directly on the beach were used.

This document compares the results obtained in the detection of plastics as well as estimation of plastics, between different architectures of artificial neural networks.

##Objetivos
sí pues, este trabajo se centra en, la detección y estimación de microplásticos, por medio de imágenes tomadas directamente en las playas. Para ello hay que interpretar los datos recogidos por el grupo EOMAR para estudiar su utilización en el entrenamiento de redes neuronales, con el fin de definir una estrategia. 

Una vez decidido cómo abordar el problema habrá que estructurar y etiquetar manualmente las imágenes, utilizando un programa auxiliar,  para adaptarlas a la estrategia tomada determinando la máscara delimitadora de las zonas con microplásticos en la arena. 

Además, habrá que realizar diversas pruebas a la espera de obtener unos resultados más afines a la realidad. Tras distintas pruebas habrá que contrastar y evaluar los distintos resultados obtenidos, tanto para las predicciones de la zona delimitada para plástico como la estimación de los gramos. 

## Contenidos del respositorio
En este repositorio se encuentra tanto el programa auxiliar para el mapeo de las imágenes como los cuadrenos Jupyter donde se realizaron las distintas pruebas.
