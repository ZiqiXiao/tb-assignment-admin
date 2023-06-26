pnpm run build:prod
rm -rf /opt/homebrew/var/www/*
cp -r dist/* /opt/homebrew/var/www/