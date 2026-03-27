@echo off
setlocal EnableDelayedExpansion

chcp 65001 > nul
set "RAIZ=%~dp0"
set "ORIGEM=%RAIZ%src\main\java"
set "DESTINO=%RAIZ%bin"

set "ARQUIVOS="
for /r "%ORIGEM%" %%f in (*.java) do (
    set "ARQUIVOS=!ARQUIVOS! "%%f""
)

javac -encoding UTF-8 -d "%DESTINO%" !ARQUIVOS!
if errorlevel 1 exit /b 1

java -Dfile.encoding=UTF-8 -Dstdout.encoding=UTF-8 ^
    -Dstderr.encoding=UTF-8 -cp "%DESTINO%" br.com.unipar.atividade.Main
