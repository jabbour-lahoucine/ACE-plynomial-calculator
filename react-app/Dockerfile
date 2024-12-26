
FROM node:14


WORKDIR /app


COPY package.json package-lock.json ./

RUN npm install

COPY . .

RUN npm run build

RUN npm install -g serve

EXPOSE 3004

CMD ["serve", "-s", "build", "-l", "3004"]