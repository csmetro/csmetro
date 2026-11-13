@echo off
setlocal enabledelayedexpansion
chcp 65001 >nul
echo =========================================
echo 正在把 models 下的 mtr: 替换为 csmetro:
echo =========================================

set "DIR=src\main\resources\assets\csmetro"

if not exist "%DIR%" (
    echo 找不到目录：%DIR%
    pause
    exit /b
)

:: 遍历所有 json 文件
for /r "%DIR%" %%f in (*.json) do (
    set "FILE=%%f"
    set "TEMP=%%~dpnf.tmp"
    
    :: 用 PowerShell 做文本替换，兼容 UTF-8
    powershell -NoProfile -Command "(Get-Content -LiteralPath '%%f' -Raw -Encoding UTF8) -replace 'mtr:','csmetro:' | Set-Content -LiteralPath '%%~dpnf.tmp' -Encoding UTF8"
    
    move /Y "%%~dpnf.tmp" "%%f" >nul
    echo [OK] !FILE!
)

echo.
echo =========================================
echo 替换完成！所有模型中的 mtr: 已改为 csmetro:
echo =========================================
pause