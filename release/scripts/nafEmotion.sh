#!/usr/bin/env bash


java -Xmx2000m -cp ../lib/emotiontagger-v1.0-jar-with-dependencies.jar EmotionTagger --naf "../example/78496_Airbus_A380_test_flight_delayed_after_accident.naf"  --emotion-lexicon "../resources/NRC-emotion-lexicon-wordlevel-alphabetized-v0.92.txt" --intensifiers "../resources/intensifiers.txt"  --intensifiers "../resources/weakeners.txt" > "../example/78496_Airbus_A380_test_flight_delayed_after_accident.emo.naf"
