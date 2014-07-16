Burraco-Tournament
==================

A Java App for Windows, created to help users to create and office a Burraco Tournament
Versione 1.1.1

Changelog
====
Versione 1.3:

- CHANGE: Ora tutti gli oggetti interni sono rappresentati con una referenza tramite handle, non piu id
- BUGFIX: errore nell'update dei singoli nel database, ora impossibili utenti ricorrenti

Versione 1.2.1:

- NEW: L'approccio client server lavora anche attraverso internet, modificato comportamento socket in modo che il client sia sempre client

Versione 1.1.1:

- BUGFIX: Errato id prelevato dalla tabella contestuale di interfaccia con il database dei tornei e dei giocatori

Versione 1.1:

- implementata l'interfaccia di salvataggio web , e le preferenze



TODO
==============

- Implementazione numero 4 smazzate
- Stampa dei fogli
- interfacciamento con database di singoli utenti con tracking dei tornei giocati
- thread save automatico
- interfacciamento con cartella utente windows/mac/linux con save delle impostazioni
- sync with dropbox
- applicazione lato server per interfaccia php per aggiornamenti in tempo reale
- implementazione counter minuti per turno

BUG CONOSCIUTI
=========
- ogni tanto il server ha problemi con il parsing dei file temporanei
