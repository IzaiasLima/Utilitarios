## Verifica DV de conta e CPF
 
def dv(nr):
  d = sum([i*int(x) for i, x in enumerate(nr.rjust(10,'0'))]) % 11
  return str(0 if d > 9 else d)

def dvcpf(nr):
  d = dv(nr)
  return '%s%s' % (d, dv(nr+d))

def checaDV(cta):
  return dv(cta[:-1]) == cta[-1]

def checaCPF(cpf):
  return dvcpf(cpf[:-2]) == cpf[-2:]


def test():
  conta = '4540280'
  cpf = '178290543'

  print('%s-%s' % (conta, dv(conta)))
  print('%s-%s' % (cpf, dvcpf(cpf)))

  contaDV = conta + dv(conta)
  cpfDV = cpf + dvcpf(cpf)
  cpfERR = '17829054305'

  print(checaDV(contaDV))
  print(checaCPF(cpfDV))
  print(checaCPF(cpfERR))

if __name__ == "__main__":
  test()
