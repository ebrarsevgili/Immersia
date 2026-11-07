# ---- Build stage: create the static production build with Vite ----
FROM node:22-alpine AS build
WORKDIR /app

# Install dependencies first so they are cached until package files change
COPY package.json package-lock.json ./
RUN npm ci

COPY . .
RUN npm run build

# ---- Runtime stage: serve the static files with nginx ----
FROM nginx:alpine
COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=build /app/dist /usr/share/nginx/html

EXPOSE 80
