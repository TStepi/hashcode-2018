

class Read:
    def __init__(self, file, encoding='utf-8'):
        self.file = file
        self.encoding = encoding
        self.lines = self.read(file, encoding)
        self.header = None

    def read(self, file, encoding='utf-8'):
        with open(file,'r',encoding=self.encoding) as f:
            lines = iter(f.readlines())
            if not lines:
                return []
            ## header = lines.next()
            return [line.strip() for line in lines]

    def getLines(self):
        return self.lines
