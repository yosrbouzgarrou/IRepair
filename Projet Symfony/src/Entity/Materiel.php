<?php

namespace App\Entity;

use App\Repository\MaterielRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=MaterielRepository::class)
 */
class Materiel
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $typePiece;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $probleme;

    /**
     * @ORM\Column(type="datetime", nullable=true)
     */
    private $dateDistribution;

    /**
     * @ORM\ManyToOne(targetEntity=Compte::class, inversedBy="materiels")
     * @ORM\JoinColumn(nullable=false)
     */
    private $compte;

    /**
     * @ORM\OneToMany(targetEntity=Facture::class, mappedBy="materiel")
     */
    private $factures;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $nom;

    public function __construct()
    {
        $this->factures = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getTypePiece(): ?string
    {
        return $this->typePiece;
    }

    public function setTypePiece(string $typePiece): self
    {
        $this->typePiece = $typePiece;

        return $this;
    }

    public function getProbleme(): ?string
    {
        return $this->probleme;
    }

    public function setProbleme(string $probleme): self
    {
        $this->probleme = $probleme;

        return $this;
    }

    public function getDateDistribution(): ?\DateTimeInterface
    {
        return $this->dateDistribution;
    }

    public function setDateDistribution(?\DateTimeInterface $dateDistribution): self
    {
        $this->dateDistribution = $dateDistribution;

        return $this;
    }

    public function getCompte(): ?Compte
    {
        return $this->compte;
    }

    public function setCompte(?Compte $compte): self
    {
        $this->compte = $compte;

        return $this;
    }

    /**
     * @return Collection<int, Facture>
     */
    public function getFactures(): Collection
    {
        return $this->factures;
    }

    public function addFacture(Facture $facture): self
    {
        if (!$this->factures->contains($facture)) {
            $this->factures[] = $facture;
            $facture->setMateriel($this);
        }

        return $this;
    }

    public function removeFacture(Facture $facture): self
    {
        if ($this->factures->removeElement($facture)) {
            // set the owning side to null (unless already changed)
            if ($facture->getMateriel() === $this) {
                $facture->setMateriel(null);
            }
        }

        return $this;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }
}
